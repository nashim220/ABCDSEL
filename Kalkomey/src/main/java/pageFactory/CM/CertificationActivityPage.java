package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class CertificationActivityPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public CertificationActivityPage(WebDriver driver)
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
	
	
	
	    @FindBy(css="#activity-stream header")
	    public WebElement Header;
	
		@FindBy(css="#agency_id")
    	public WebElement Agency;
		
		public void SelectAgency(String agency)
		{
			new Select(Agency).selectByVisibleText(agency);
		}
		
		@FindBy(css="#search-form button")
    	public WebElement Search;
		
		public void ClickOnSearchButton()
		{
			Search.click();
		}
		
		@FindBy(css="#activity-page ul")
    	public WebElement Pagination;
		
		@FindBy(css=".pagination.pagination li:nth-of-type(4) a")
	    public WebElement SpecificPage;
		
		public void ClickOnSpecificPage()
		{
			SpecificPage.click();
		}
		
		@FindBy(css=".next a")
	    public WebElement Next;
		
		public void ClickOnNextButton()
		{
			Next.click();
		}	
		
		@FindBy(css=".empty-activity p")
	    public WebElement NoActivity;
				
		@FindBy(css="#activity-stream ul li")
	    public WebElement Result;
		
		@FindBy(css="#activity-stream i.fa.fa-certificate")
	    public WebElement IconCreateCertification;
		
		@FindBy(css=".fa.fa-comment")
	    public WebElement IconNote;
		
		@FindBy(css=".fa.fa-refresh")
	    public WebElement IconOrder;
		
		@FindBy(css="#activity-stream li:nth-of-type(1) p a")
	    public WebElement User;
		
		@FindBy(css="#activity-stream > ul > li:nth-of-type(4) > div.span10 > h1 > a")
		public WebElement  UserNameLink;

		public void ClickOnUserNameLink()
		{
			UserNameLink.click();
		}
		
		@FindBy(css="div.span8>h1")
		public WebElement  Userpage;	
		
		
		@FindBy(css="#note_note_text")
		public WebElement  Addnotesection;
		
		public void EnterAddnote(String note)
		{
			Addnotesection.sendKeys(note);
		}
		
		@FindBy(css="#new_note > button")
		public WebElement  AddNoteButton;
		
		public void ClickOnAddNoteButton()
		{
			AddNoteButton.click();
		}
		
		//@FindBy(css="#search-form > div > fieldset.span6 > input[type='text']]")
		@FindBy(css="fieldset.span4>input")
		public WebElement  StudentInfoTextBox;
		
		public void EnterStudentInfo(String date)
		{
			StudentInfoTextBox.sendKeys(date);
		}
		@FindBy(css="button.btn.btn-primary")
		public WebElement SearchButton;
	
		
		public void  ClickOnSearchBtn()
		{
			SearchButton.click();
		}
		@FindBy(css="ol > li:nth-of-type(1) > header > div.span11 > h4 > a")
		public WebElement  Firstuserlink;
		public void  ClickOnFirstuserlink()
		{
			Firstuserlink.click();
		}
		
		@FindBy(css="li:nth-of-type(1) > div.span10 > h1 > a")
		public WebElement Firstuserentry;
		
		public void ClickOnFirstuserentry()
		{
			Firstuserentry.click();
		}
		
		
		@FindBy(css="#activity-stream > header")
		public WebElement ActivityPage;

		
		@FindBy(css="#contact-info > header > div > a")
		public WebElement  EditTab;
		
		public void ClickOnEditTab()
		{
			EditTab.click();
		}
		@FindBy(css="#note_note_text")
		public WebElement AddNoteText;
		
		public void EnterAddNoteText(String addnote)
		{
			AddNoteText.sendKeys(addnote);
		}
		
		@FindBy(css="#new_note > button")
		public WebElement AddNoteBtn;
		
		public void ClickOnAddNoteBtn()
		{
			AddNoteBtn.click();
		}
		@FindBy(css="p.note-text")
		public WebElement AddedNote;
		
		@FindBy(css="#notes > header > h3")
		public WebElement  NoteHeader;
		
		@FindBy(css="#notes > div > div.empty-notes")
		public WebElement EmptyNote;
		
	
		@FindBy(css="li:nth-of-type(1)>p.note-text")
		public WebElement LatestEntyNote;
		
		@FindBy(css="ul > li.active > a > span > span > p")
		public WebElement CertificationManager;
		
		public void ClickOnCertificationManager()
		{
			CertificationManager.click();
		}
		@FindBy(css="ul>li:nth-of-type(1)>div.span10>h1>a")
		public  WebElement FirstActivityUser;
		
		public void ClickOnFirstActivityUser()
		{
			FirstActivityUser.click();
		}
		@FindBy(css="#activity-stream>header")	
		public WebElement  RightSideActivity;
		
		
		 @FindBy(css="li:nth-of-type(1)>div.span10>p>button")
		  public WebElement   FirstActivitydetails;
		 
		 public void  ClickOnFirstActivitydetails()
		 {
			 FirstActivitydetails.click();
		 }
		@FindBy(css="#details-0>dl>dt")
		public WebElement  ActivityDetails;
		
		@FindBy(css="a[href='/certifications/activity']")
		public WebElement CertificationActivity;
		public void ClickOnCertificationActivity()
		{
			CertificationActivity.click();
		}
		@FindBy(css="button.btn.btn-link")
		public WebElement DetailsActivity;
		
		public void ClickOnDetailsActivity()
		{
			DetailsActivity.click();
		}
		
		@FindBy(css="dt.quantity")
		 public WebElement Details;
		
		
}
