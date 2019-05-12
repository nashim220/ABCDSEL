package pageFactory.CM;

import java.awt.Robot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class ReportPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ReportPage(WebDriver driver)
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
	

		@FindBy(css="a[href='/reports']")
		public WebElement Report;
	
		@FindBy(css="#showHide span")
		//@FindBy(css="#showHide > span:nth-child(1)")
		public WebElement Title;
		public void  ClickOnTitle()
		{
			
			
			Title.click();
			
			
		}
		 @FindBy(css="a.accordion-toggle.reports-menu-toggle")
		 public WebElement  ReportTitle;
		 public void  ClickOnReportTitle()
			{
			 ReportTitle.click();
			}
		
		@FindBy(css="#formContent[style='height: 0px;']")
		public WebElement HideTitle;
		
		@FindBy(css="#formContent[style='height: auto;']")
		public WebElement UnHideTitle;		
		
		
		@FindBy(css="#state")
    	public WebElement State;
		
		public void SelectState(String state)
		{
			new Select(State).selectByVisibleText(state);
		}
		
		@FindBy(css="#certification_type_id")
    	public WebElement Type;
		
		public void SelectType(String type)
		{
			new Select(Type).selectByVisibleText(type);
		}
		
		@FindBy(css="#reportType span:nth-of-type(1) input")
    	public WebElement FromDate;
		
		public void EnterFromDate(String fromdate)
		{
			FromDate.clear();
			FromDate.sendKeys(fromdate);
		}
		
		@FindBy(css="#reportType span:nth-of-type(2) input")
    	public WebElement ToDate;
		
		public void EnterToDate(String todate)
		{
			ToDate.clear();
			ToDate.sendKeys(todate);
		}
		@FindBy(css="#reportType button")
    	public WebElement Generate;
		
		public void ClickOnGenerateButton()
		{
			Generate.click();
		}
		
		@FindBy(css="#reports li:nth-of-type(1) a")
    	public WebElement DashboardTab;
		
		public void ClickOnDashboardTab()
		{
			DashboardTab.click();
		}
		
		@FindBy(css="#completions_total")
    	public WebElement Total;
		
		@FindBy(css="#reports li:nth-of-type(2) a")
    	public WebElement DetailsTab;
		
		public void ClickOnDetailsTab()
		{
			DetailsTab.click();
		}
		
		@FindBy(css="#courseCompletions")
    	public WebElement CourseCompletionGraph;
		
		@FindBy(css="#ageDistributions")
    	public WebElement AgeDistributionGraph;
		
		@FindBy(css="#adultsMinors")
    	public WebElement AgeThreshold;
		
		@FindBy(css="#genderCounts")
    	public WebElement GenderCount;
		
		@FindBy(css="#reports table")
    	public WebElement ClassTypeByStatus;
		
		@FindBy(css=".well")
    	public WebElement Graph;
		
		
		//Details Page
		
		@FindBy(css="#grid div:nth-of-type(3)")
    	public WebElement Pagination;

		
		@FindBy(css="#grid th:nth-of-type(1) a:nth-of-type(2)")
    	public WebElement CertifiedOn;
		
		@FindBy(css="#grid th:nth-of-type(2) a:nth-of-type(2)")
    	public WebElement FullName;
		
		@FindBy(css="#grid th:nth-of-type(3) a:nth-of-type(2)")
    	public WebElement DOB;
		
		@FindBy(css="#grid th:nth-of-type(4) a:nth-of-type(2)")
    	public WebElement Email;
		
		@FindBy(css="#grid th:nth-of-type(5) a:nth-of-type(2)")
    	public WebElement Phone;
		
		@FindBy(css="#grid th:nth-of-type(6) a:nth-of-type(2)")
    	public WebElement Gender;
		
		@FindBy(css="#grid th:nth-of-type(7) a:nth-of-type(2)")
    	public WebElement Internet;
		
		@FindBy(css="#grid th:nth-of-type(8) a:nth-of-type(2)")
    	public WebElement PhyAdd;
		
		@FindBy(linkText="Download CSV")
    	public WebElement DownloadCSV;
		
		public void ClickOnDownloadCSVButton()
		{
			DownloadCSV.click();
		}
		
		@FindBy(css="div:nth-of-type(4)>div.span6:nth-of-type(1)>h3")
		public WebElement ClassTypByStatusHeader;
		
		@FindBy(css="thead>tr>th:nth-of-type(1)")
		public WebElement ClassType;
		
		@FindBy(css="thead>tr>th:nth-of-type(2)")
		public WebElement Pass;
		
		@FindBy(css="thead>tr>th:nth-of-type(3)")
		public WebElement Fail;
		
		@FindBy(css="thead>tr>th:nth-of-type(4)")
		public WebElement NoShow;
		
		@FindBy(css="thead>tr>th:nth-of-type(5)")
		public WebElement Incomplete;
		
		@FindBy(css="thead>tr>th:nth-of-type(6)")
		public WebElement Other;
		
		@FindBy(css="thead>tr>th:nth-of-type(7)")
		public WebElement Total1;
		
		
		@FindBy(css="tbody>tr:nth-of-type(1)>td:nth-of-type(1)")
		public WebElement ClassRoomState;
		
		@FindBy(css="tbody>tr:nth-of-type(2)>td:nth-of-type(1)")
		public WebElement Internet1;
		
		@FindBy(css="thead>tr>th:nth-of-type(4)>a:nth-of-type(1)>span")
		public WebElement CertifiedOnLink;
		
		public void ClickOnCertifiedOnLink()
		{
			CertifiedOnLink.click();
		}
		
		@FindBy(css="div:nth-of-type(1) span:nth-of-type(1) + input")
		public WebElement FilterValue1;
		
		public void  EnterFilterValue1(String value)
		{
			FilterValue1.clear();
			FilterValue1.sendKeys(value);
		}
		
		@FindBy(css="div:nth-of-type(1) span:nth-of-type(3) + input")
		public WebElement FilterValue2;
		
		public void  EnterFilterValue2(String value)
		{
			FilterValue2.clear();
			FilterValue2.sendKeys(value);
		}
		
		@FindBy(css="div:nth-of-type(2) button:nth-of-type(1)")
		public WebElement  FilterButton;
		
		public void ClickOnFilterButton()
		{
			FilterButton.click();
		}
		
		@FindBy(css="div:nth-of-type(1) div:nth-of-type(2) button:nth-of-type(2)")
		public WebElement  ClearButton;
		
		public void ClickOnClearButton()
		{
			ClearButton.click();
		}
		
		@FindBy(css="span.k-icon.k-i-arrow-w")
		public WebElement PreviousBtn;
		
		public void ClickOnPreviousBtn()
		{
			PreviousBtn.click();
		}
		
		@FindBy(css="span.k-icon.k-i-arrow-e")
		public WebElement NextBtn;
		
		public void ClickOnNextBtn()
		{
			NextBtn.click();
		}
		
		@FindBy(css="div:nth-of-type(3) ul li:nth-of-type(3) a")
		public WebElement SpecificPage;
		
		public void ClickOnSpecificPageNumber()
		{
			SpecificPage.click();
		}
		
		@FindBy(css="#completions_total")
		public WebElement Totalcount;
		
		@FindBy(css="span.k-pager-info.k-label")
		public WebElement  DetailsCount;
		
		@FindBy(css="span.k-state-selected")
		public WebElement PageNoSelected;
				
		@FindBy(css="#grid > div.k-grid-content > table > tbody > tr:nth-child(1)> td:nth-child(4)")
		public WebElement FilterData;
		
		
	}
