	package pageObjects;
	
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	
	public class ConfigurationInvoiceSettings {
		
		public ConfigurationInvoiceSettings(){
			
		}
	
		private static WebElement webElement = null;
			
		//TODO: This is to get the Configuration Link in left navigation.
		public WebElement fn_ConfigurationLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Configuration")));
			return webElement;
		}
		
		//TODO: This is to get the Integration Link in left navigation.
		public WebElement fn_IntegrationLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Integrations")));
			return webElement;
		}
		
		//TODO: This is to get the Billing Link in left navigation.
		public WebElement fn_BillingLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Billing")));
			return webElement;
		}
		
		//TODO: This is to get the Invoice Settings Link in left navigation.
		public WebElement fn_InvoiceSettingsLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Invoice")));
			return webElement;
		}		
		
		//TODO: This is to get the Countries Link in left navigation.
		public WebElement fn_Countries(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Countries")));
			return webElement;
		}
		
		//TODO: To identify row with IE country code row
		public WebElement fn_IECountrycode(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@id='countryIndex_row_IE']/td")));
			return webElement;
		}
		
		//TODO: To identify Default Currency field
		public WebElement fn_DefaultCurrency(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("country_currencyCd")));
			return webElement;
		}		
		
		//TODO: To identify promotional plan set field
		public WebElement fn_PromotionalPlanSet(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("country_defPlanTypeNo")));
			return webElement;
		}
		
		//TODO: To identify Close Button
		public WebElement fn_CloseBtn(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Close']")));
			return webElement;
		}
		
		//TODO: To identify Client Settings Link
		public WebElement fn_ClientSettingsLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Client Settings")));
			return webElement;
		}
		
		//TODO: To identify Functional Accounts Group Link
		public WebElement fn_FunAcctGroupLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Functional Account Groups")));
			return webElement;
		}
		
		//TODO : To identify Group Name field
		public WebElement fn_GroupNameField(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("ClientAcctGroup_group_name")));
			return webElement;
		}
		
		//TODO : To identify Client Group ID field
		public WebElement fn_ClientGrpIDField(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("ClientAcctGroup_client_group_id")));
			return webElement;
		}	
		
		public WebElement fn_GrpPrefix(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("ClientAcctGroup_mask")));
			return webElement;
		}
				
		//TODO : To identify row from functional accounts group corresponding to IE
		public WebElement fn_IEtableLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@id='planGroupsIndex_row_436']/td")));
			return webElement;
		}
		
		
		//TODO : To identify Starting Sequence Number field
		public WebElement fn_StartingSeqNumField(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Starting Sequence Number']/following::input")));
			return webElement;
		}
		
		//TODO : To identify Notification Template Group field
		public WebElement fn_NotificationTempGrpField(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("ClientAcctGroup_notify_tmplt_grp_seq_id")));
			return webElement;
		}		
		
		//TODO : To identify close button
		public WebElement fn_CloseButton(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Close']")));
			return webElement;
		}	

		
		//TODO: This is to get the Countries Link in left navigation.
		public WebElement fn_CountriesLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Countries")));
			return webElement;
		}
		
		//TODO: This is to get the Currencies Link in left navigation.
		public WebElement fn_CurrenciesLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Currencies")));
			return webElement;
		}

	
		//TODO: This is to click on the close button.
		/*public WebElement fn_CloseButton(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ajaxcontent\"]/div[2]/span[2]/input")));
			return webElement;
		}*/
		
		//TODO: This is to Click the Accepted Processors tab under currencies tab
		public WebElement fn_AcceptedProcessorsTab(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accepted Processors")));
			return webElement;
		}
		
		//TODO: This is to Click the Taxation Configuration Link
		public WebElement fn_TaxationConfigurationLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Taxation Configuration")));
			return webElement;
		}
		
		//TODO: This is to Click the fn_InternalValues Link
		public WebElement fn_InternalValuesLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Internal Values")));
			return webElement;
		}
		
		//TODO: This is to Click the fn_InternalValues Link
		public WebElement fn_NextLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"DataTables_Table_0_next\"]")));
			return webElement;
		}

	
	}
