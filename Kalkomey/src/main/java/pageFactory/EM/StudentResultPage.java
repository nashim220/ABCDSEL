package pageFactory.EM;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class StudentResultPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public StudentResultPage(WebDriver driver)
	{	 
        this.driver = driver;
 
        //This initElements method will create all WebElements
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.WebDriverWaitDuration), this);
	}
	
	 
	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 	
	
		@FindBy(css=".mtn.heading-inline.h2")
		public WebElement Title;
	
		@FindBy(css="#result-subnav div:nth-of-type(1) a")
		WebElement EditEventInfo;
		
		//Click on Edit Event Info
		public void ClickOnEditEventInfoButton()
		{
			EditEventInfo.click();
		}
		
		//Click on Cancel Link
		
		@FindBy(css=".pull-left.text.text-error")
		WebElement Cancel;
		
		//Click on Cancel Link
		public void ClickOnCancelLink()
		{
			Cancel.click();
		}
				
		@FindBy(css="#result-subnav div:nth-of-type(2) div:nth-of-type(2) div a")
		WebElement EditInstrInfo;
		
		//Click on Edit Instructor Info
		public void ClickOnEditInstrInfoButton()
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", EditInstrInfo);
			EditInstrInfo.click();
		}
			
		
		@FindBy(css="#result-subnav div:nth-of-type(3) a")
		WebElement EditStudResult;
		
		//Click on Edit Student Result
		public void ClickOnEditStudResultButton()
		{
			EditStudResult.click();
		}
		
		
		@FindBy(css="#result-subnav div:nth-of-type(1) span")
		public WebElement EventInfoStatus;
		
		@FindBy(css="#result-subnav div div:nth-of-type(2) span")
		public WebElement InstructorInfoStatus;
		
		@FindBy(css="#result-subnav div div:nth-of-type(3) span")
		public WebElement StudentResultStatus;
		
		@FindBy(css="input[id$='No Show'][class='results-final-grade']")
		public WebElement FinalGradeNoShow;
	
	
		@FindBy(css=".results-final-grade")
		public WebElement FinalGrade;
		
		@FindBy(css="input[id$='NA'][class='results-field-test']")
		public WebElement FieldTestNA;
		
		@FindBy(css="input[id$='Pass'][class='results-field-test']")
		public WebElement FieldTestPass;
		
		@FindBy(css="input[id$='Incomplete'][class='results-field-test']")
		public WebElement FieldTestIncomplete;
		
		@FindBy(css="input[id$='Fail'][class='results-field-test']")
		public WebElement FieldTestFail;
		
		
		@FindBy(css="input[id$='Pass'][class='results-live-fire']")
		public WebElement LiveFirePass;
		
		@FindBy(css="input[id$='Fail'][class='results-live-fire']")
		public WebElement LiveFireFail;
		
		@FindBy(css="input[id$='Incomplete'][class='results-live-fire']")
		public WebElement LiveFireIncomplete;
		
		@FindBy(css="input[id$='NA'][class='results-live-fire']")
		public WebElement LiveFireNA;
		
		@FindBy(css="input[id^='results'][class='results-written-exam']")
		public WebElement WriteExam;
		
		
		@FindBy(css="input[id$='Pass'][class='results-final-grade']")
		public WebElement FinalGPass;
		
		
		@FindBy(css="input[id$='Fail'][class='results-final-grade']")
		public WebElement FinalGFail;
		
		@FindBy(css= "input[id$='Incomplete'][class='results-final-grade']")
		public WebElement FinalGIncomplete;
		
		@FindBy(css= "input[id$='Attend'][class='results-final-grade']")
		public WebElement FinalGAttend;
		
		@FindBy(css= "input[id^='results'][class='results-certificate-number']")
		public WebElement CertificateNumber;
		
		
		public void SelectFinalGrade()
		{
			new Select(FinalGrade).selectByVisibleText("Pass");
			
		}
		/*Fill Event Information*/
		
		@FindBy(css="#ReportDetail_class_hours")
		WebElement ClassHours;
		
		//Enter Class Hour
		public void EnterClassHours(String hours)
		{
			ClassHours.clear();
			ClassHours.sendKeys(hours);
		}
		
		
		@FindBy(css="#ReportDetail_organization")
		WebElement TaughtBy;
		
		public void SelectTaughtBy()
		{
			new Select(TaughtBy).selectByVisibleText("Volunteer Instructor");
			
		}
		
		@FindBy(css="#ReportDetail_field_hours")
		WebElement RangeHours;
		
		//Enter Range Hour
		public void EnterRangeHours(String rangehours)
		{
			RangeHours.clear();
			RangeHours.sendKeys(rangehours);
		}
		
		@FindBy(css="input[id$='instructor_field_hrs'][class='input-small']")
		WebElement RangeHours2;
		
		//Enter Range Hour
		public void EnterRangeHours2(String rangehours)
		{
			RangeHours2.clear();
			RangeHours2.sendKeys(rangehours);
		}
		
		
		
		@FindBy(css="#ReportDetail_comments")
		WebElement Comments;
		
		//Enter Comments
		public void EnterComments(String comments)
		{
			Comments.clear();
			Comments.sendKeys(comments);
		}
		
		
		@FindBy(css=".btn.btn-primary.pull-right")
		WebElement Save;
		
		//Click on Save Event Info
		public void ClickOnSaveButton()
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Save);
			Save.click();
		}
		
		
		/*Fill Instructor Information*/
		
		//Instructor Prep Hours
		@FindBy(css="input[id$='prep_hrs']")
		public WebElement PrepHours;
		
		//Enter Range Hour
		public void EnterPrepHours(String prephours)
		{
			PrepHours.clear();
			PrepHours.sendKeys(prephours);
		}
		
		//Instructor Class Hours
		@FindBy(css="input[id$='class_hrs']")
		public WebElement ClassHrs;
		
		//Enter Class Hour
		public void EnterClassHrs(String classhours)
		{
			ClassHrs.clear();
			ClassHrs.sendKeys(classhours);
		}
		
		//Instructor Field Hours		
		@FindBy(css="input[id$='field_hrs']")
		public WebElement FieldHrs;
		
		//Enter Field Hour
		public void EnterFieldHrs(String fieldhrs)
		{
			FieldHrs.clear();
			FieldHrs.sendKeys(fieldhrs);
		}
		
		//Instructor Travel Hours		
		@FindBy(css="input[id$='travel_hrs']")
		public WebElement TravelHrs;
		
		//Enter Travel Hour
		public void EnterTravelHrs(String travelhrs)
		{
			TravelHrs.clear();
			TravelHrs.sendKeys(travelhrs);
		}
		
		//Instructor Travel Hours		
		@FindBy(css="input[id$='training_hrs']")
		public WebElement TrainingHours;
				
		//Enter Travel Hour
		public void EnterTrainingHours(String traininghrs)
		{
			TrainingHours.clear();
			TrainingHours.sendKeys(traininghrs);
		}		
			
				
		//Instructor Admin Hours		
		@FindBy(css="input[id$='admin_hrs']")
		public WebElement AdminHrs;
				
		//Enter Admin Hour
		public void EnterAdminHrs(String Adminhrs)
		{
			AdminHrs.clear();
			AdminHrs.sendKeys(Adminhrs);
		}
		
		
		//Total Course Hours	
		@FindBy(id="ReportDetail_total_course_hours")
		public WebElement TotalHrs;
		
		//Firearm Safety Hours	
		@FindBy(id="ReportDetail_firearm_safety_hours")
		public WebElement FirearmHrs;		
		
		//Laws and Regulations Hours
		@FindBy(id="ReportDetail_laws_and_regs_hours")
		public WebElement LawHrs;				
		
		//Responsibility and Ethics Hours
		@FindBy(id="ReportDetail_resp_and_ethics_hours")
		public WebElement EthicHrs;	
		
		//Wildlife Identification Hours 
		@FindBy(id="ReportDetail_wildlife_ident_hours")
		public WebElement WildlifeIdenHrs;			
	
		//Survival Hours 
		@FindBy(id="ReportDetail_survival_hours")
		public WebElement SurvivalHrs;
		
		//Other Hours 
		@FindBy(id="ReportDetail_survival_hours")
		public WebElement OtherlHrs;		
		
		
		//Enter Variety of Hours
		public void EnterVarietyHours()
		{
			TotalHrs.clear();
			TotalHrs.sendKeys("1.1");
			FirearmHrs.clear();
			FirearmHrs.sendKeys("1.25");
			LawHrs.clear();
			LawHrs.sendKeys("1.5");
			EthicHrs.clear();
			EthicHrs.sendKeys("1.98");
			WildlifeIdenHrs.clear();
			WildlifeIdenHrs.sendKeys("2");
			SurvivalHrs.clear();
			SurvivalHrs.sendKeys("2");
			OtherlHrs.clear();
			OtherlHrs.sendKeys("1.75");
		
		}	
	
		
		
		
		//Instructor Pin		
		@FindBy(css="input[id^='instructor_pin']")
		public WebElement InstrPin;
		
		//Enter Range Hour
		public void EnterInstrPin(String instrpin)
		{
			InstrPin.clear();
			InstrPin.sendKeys(instrpin);
		}		
					
		@FindBy(css=".text.text-warning")
		WebElement ClearForm;
		
		//Click on Clear Form Link
		public void ClickOnClearFormLink()
		{
			ClearForm.sendKeys(Keys.RETURN);
		}						
		
		
		@FindBy(css=".btn.btn-success")
		WebElement ReviewAndSubmit;
		
		
		public void ClickOnReviewAndSubmitButton()
		{
			ReviewAndSubmit.click();
		}							
		
		@FindBy(css=".btn.btn-success.media-object")
		WebElement FinalSubmit;
		
		
		public void ClickOnFinalSubmitButton()
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", FinalSubmit);
			FinalSubmit.click();
		}							
		
		@FindBy(css=".btn.btn-primary.mtt")
		WebElement ReviewAndApprove;
		
		
		public void ClickOnReviewAndApproveButton()
		{
			ReviewAndApprove.click();
		}
		
		@FindBy(css=".btn.btn-success")
		WebElement Approve;
		
		
		public void ClickOnApproveButton()
		{
			Approve.click();
		}
		
		@FindBy(css=".page-header>h3")
		public WebElement ReviewAndSubmitPageHeader;
		
		@FindBy(css=".alert-flash.alert.alert-success")
		public WebElement ResultSubmitSuccess;
		
		
		
		@FindBy(css=".checkbox input")
		WebElement DisablePIN;
		
		
		public void ClickOnDisablePINCheckBox()
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", DisablePIN);
			DisablePIN.click();
		}
		
		
		@FindBy(css=".file-upload input")
		public WebElement SendPath;
		
		public void SendPath(String path)
		{
			SendPath.clear();
			SendPath.sendKeys(path);
		}	
		
		
		//Select Administrator that should receive results.
		@FindBy(css="#selected_admin_id")
		public WebElement SelectAdmin;
		
		public void SelectAdmin(String admin)
		{
			new Select(SelectAdmin).selectByVisibleText(admin);
			
		}
		
		//Demographic Information
		
		@FindBy(css=".row-fluid>h4")
		public WebElement DemographicInformation;
		
		@FindBy(css=".dl-horizontal:nth-of-type(1)>dt:nth-of-type(1)")
		public WebElement option1;
		
		@FindBy(css=".dl-horizontal:nth-of-type(1)>dt:nth-of-type(2)")
		public WebElement option2;
		
		@FindBy(css="#review-results dl:nth-of-type(1) dt:nth-of-type(3)")
		public WebElement option3;
		
		@FindBy(css=".dl-horizontal:nth-of-type(2)>dt:nth-of-type(1)")
		public WebElement male;
		
		@FindBy(css=".dl-horizontal:nth-of-type(2)>dt:nth-of-type(2)")
		public WebElement female;
		
		
		// Student result summary page
		@FindBy(css=".page-header.page-header-compressed>h2")
		public WebElement summaryPageTitle;
		
		@FindBy(css="#jqgh_data_event_capacity")
		public WebElement eventCapacity;
		
		@FindBy(css="#jqgh_data_registered")
		public WebElement registered;
		
		@FindBy(css="#sdate")
		public WebElement startDate;
		
		public void clickOnStartDate()
		{
			startDate.click();
		}	
		
		@FindBy(xpath=".//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td[5]/a")
		public WebElement date;
		
		public void clickOnDate()
		{
			date.click();
		}
		
		@FindBy(css="#filterapply")
		public WebElement apply;
		
		public void clickOnApplyButton()
		{
			apply.click();
		}
		
		
		
		
	}
