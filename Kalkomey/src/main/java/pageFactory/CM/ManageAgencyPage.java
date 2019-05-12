package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class ManageAgencyPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ManageAgencyPage(WebDriver driver)
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
	
	
	
		@FindBy(css=".widget-header h3")
		public WebElement Title;
		
		
		@FindBy(css="#search-form input")
		public WebElement AgencySearchText;
		
		public void EnterAgencySearchText(String text)
		{
			AgencySearchText.clear();
			AgencySearchText.sendKeys(text);
		}
	

		  
		@FindBy(css="#search-form button")
    	public WebElement Search;
		
		public void ClickOnSearchButton()
		{
			Search.click();
		}
		
	
		@FindBy(css=".index-header-action")
	    public WebElement NewAgency;
		
		public void ClickOnNewAgencyLink()
		{
			NewAgency.click();
		}	
			
		
		@FindBy(css=".alert.alert-notice")
	    public WebElement ErrorMessage;
		
		@FindBy(css=".search-results ol li")
	    public WebElement SearchRecord;
		
		@FindBy(css=".span12 h4 a")
	    public WebElement FirstRecord;
		
		@FindBy(css=".span5")
	    public WebElement DescriptionFirstRecord;
		
		@FindBy(css=".span7>p")
	    public WebElement CollectionCountFirstRecord;
		
		@FindBy(css=".next a")
	    public WebElement Next;
		
		public void ClickOnNext()
		{
			Next.click();
		}	
		
		@FindBy(css=".pagination.pagination li:nth-of-type(4) a")
	    public WebElement SpecificPage;
		
		public void ClickOnSpecificPage()
		{
			SpecificPage.click();
		}
		
		@FindBy(css=".pagination ul")
	    public WebElement Pagination;
		
		
		//New Agency
		
		@FindBy(css=".page-header h1")
	    public WebElement Header;
		
		@FindBy(css="#agency_name")
	    public WebElement AgencyName;
		
		public void EnterAgencyName(String name)
		{
			AgencyName.clear();
			AgencyName.sendKeys(name);
		}
		
		public void ClearAgencyName()
		{
			AgencyName.clear();			
		}
		
		
		@FindBy(css="#agency_description")
	    public WebElement Description;
		
		public void EnterDescription(String description)
		{
			Description.clear();
			Description.sendKeys(description);
		}
		
		public void  ClearDescription()
		{
			Description.clear();
			
		}
		
		
		@FindBy(css="#agency_is_subscriber")
	    public WebElement KPSSubscriber;
		
		public void ClickOnKPSSubscriberCheckbox()
		{
			KPSSubscriber.click();
		}
		
		@FindBy(css="#agency_allows_multiple_results")
	    public WebElement AllowMultipleResults;
		
		public void ClickOnAllowMultipleResultsCheckbox()
		{
			AllowMultipleResults.click();
		}
		
		@FindBy(css="#agency_allows_address_editable")
	    public WebElement AllowAddressEditable;
		
		public void ClickOnAllowAddressEditableCheckbox()
		{
			AllowAddressEditable.click();
		}
		
		@FindBy(name="commit")
	    public WebElement Submit;
		
		public void ClickOnSubmitButton()
		{
			Submit.click();
		}
		
		@FindBy(css="#agency-form div:nth-of-type(2) div")
	    public WebElement Error;
		
		@FindBy(css="#agency_name + span")
	    public WebElement ErrorAgencyName;
		
		@FindBy(css=".alert.alert-success")
	    public WebElement Success;
		
		@FindBy(css=".dialog h1")
	    public WebElement NotAuthorized;
		
		@FindBy(css="header.widget-header>h3")
		public  WebElement  Searchuserheader;
		
		@FindBy(css="fieldset.span4>input")
		public WebElement SearchName;
		public void EnterTexttoSearchName(String Text)
		{
			SearchName.sendKeys(Text);
		}
		
		@FindBy(css="#has-permissions>a")
		public WebElement RemoveAllPermission;
		public void ClickOnRemoveAllPermission()
		{
			RemoveAllPermission.click();
		}
		
        @FindBy(css="#has-no-permissions>p")	
         public WebElement WithoutPermission;
		
        @FindBy(css="a[href='/agencies']")
        public WebElement ManageAgencieslink;
        public void ClickOnManageAgencieslink()
		{
        	ManageAgencieslink.click();
		}
		
		@FindBy(css="#agency-form > div:nth-of-type(2) > div")
		public WebElement  AgencyErrorMessage;
		
		@FindBy(css="#agency_is_subscriber")
		public WebElement  IsAKpsSub;
		
		public void ClickOnIsAKpsSub()
		{
			IsAKpsSub.click();
		}
		
		@FindBy(css="#agency_allows_multiple_results")
		public WebElement AgecyAllowMultiple;
		
		public void ClickOnAgecyAllowMultiple()
		{
			AgecyAllowMultiple.click();
		}
		
		@FindBy(css="#agency_allows_address_editable")
		public WebElement UserCanEditAdd;
		
		public void  ClickOnUserCanEditAdd()
		{
			UserCanEditAdd.click();
		}
		
		@FindBy(css="a[href='/users']")
        public WebElement Manageuserlink;
		public void ClickOnManageuserlink()
		{
			Manageuserlink.click();
		}		
		
		@FindBy(css="a[href='/certification_collections']")
        public WebElement  manageCollectionlink;
		public void ClickOnmanageCollectionlink()
		{
			manageCollectionlink.click();
		}
		
		@FindBy(css="header.widget-header>h3")
		public WebElement SearchCertificationCollections;
		
		
		@FindBy(css="ol > li:nth-of-type(1) > div > div.span6.certification-collection-info > p:nth-of-type(1) > a")
		public WebElement FirstAgencylink;
		
		public void ClickOnFirstAgencylink()
		{
			FirstAgencylink.click();
		}
		
		@FindBy(css="div.page-header>h1")
		public WebElement AgencyPage;
		
		
		@FindBy(css=" ol > li:nth-of-type(1) > div > div.span7.user-info > p > a")
		public WebElement  FirstuserAgencyLink;
		public void ClickOnFirstuserAgencyLink()
		{
			FirstuserAgencyLink.click();
		}
		@FindBy(css="ol>li:nth-of-type(1)>header>div >h4 >a")
		public WebElement Firstusername;
		
		public void ClickOnFirstusername()
		{
			Firstusername.click();
		}
		
		@FindBy(css="#profile-general > dl > dt")
		public WebElement Agencytextpresent;
		
		
		@FindBy(css="#profile-general>dl>dd>a")
		public  WebElement AgencyLinkText;
		
		public void ClickOnAgencyLinkText()
		{
			AgencyLinkText.click();
		}
		
		@FindBy(css="body > div > h1")
		public WebElement DeniedMessage;
		
		@FindBy(css=".span7.user-info>p>a")
		public WebElement ProfileAgencyName;
		
		@FindBy(css="a[href='/agencies']")
		public WebElement ManageAgencyLink;
		
}







