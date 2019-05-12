package pageFactory.EM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class AgencyInviteUserPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public AgencyInviteUserPage(WebDriver driver)
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
	
		@FindBy(css=".checkbox input")
		WebElement Program;
		
		
		public void SelectProgram()
		{
			Program.click();
		}
	
		
		@FindBy(css="#uname")
	    WebElement UserName;
		
		public void EnterUserName(String username)
		{
			UserName.clear();
			UserName.sendKeys(username);
		}
		
		@FindBy(css="#password")
	    WebElement Password;
		
		public void EnterPassword(String password)
		{
			Password.clear();
			Password.sendKeys(password);
		}
		
		@FindBy(css="#password_confirm")
	    WebElement ConfirmPassword;
		
		public void EnterConfirmPassword(String confirmpassword)
		{
			ConfirmPassword.clear();
			ConfirmPassword.sendKeys(confirmpassword);
		}
		
		@FindBy(css="#password_hint")
		WebElement PasswordHint;
	  
		public void EnterPassHint(String passwordhint)
		{
			PasswordHint.clear();
			PasswordHint.sendKeys(passwordhint);
		}

		@FindBy(css="#secret_question")
		WebElement SecretQue;

		public void EnterSecretQue(String secretque)
		{
			SecretQue.clear();
			SecretQue.sendKeys(secretque);
		}


		@FindBy(css="#secret_answer")
		WebElement SecretAns;
		
		public void EnterSecretAns(String secretans)
		{
			SecretAns.clear();
			SecretAns.sendKeys(secretans);
		}

		@FindBy(css="#first_name")
		WebElement FirstName;
		
		public void EnterFirstName(String firstname)
		{
			FirstName.clear();
			FirstName.sendKeys(firstname);
		}

		@FindBy(css="#last_name")
		WebElement LastName;
		
		public void EnterLastName(String lastname)
		{
			LastName.clear();
			LastName.sendKeys(lastname);
		}
		
		@FindBy(css="#gender")
		WebElement Gender;
		
		public void SelectGender(String gender)
		{
			new Select(Gender).selectByVisibleText(gender);			
		}
		
		@FindBy(css="#email")
		WebElement Email;

		
		public void EnterEmail(String email)
		{
			Email.clear();
			Email.sendKeys(email);
		}
		
		
		@FindBy(css="#invite_form button")
		WebElement InviteUser;
		
		public void ClickOnInviteUserButton()
		{
			InviteUser.click();
		}
		
		
		
		
	
			public void FillInviteUserForm(
													String username,
													String password,
													String confirmpassword,
													String passwordhint,
													String secretque,
													String secretans,
													String firstname,
													String lastname,
													String gender,
													String email
													)
					{
						this.EnterUserName(username);
						this.EnterPassword(password);
						this.EnterConfirmPassword(confirmpassword);
						this.EnterPassHint(passwordhint);
						this.EnterSecretQue(secretque);
						this.EnterSecretAns(secretans);
						this.EnterFirstName(firstname);
						this.EnterLastName(lastname);
						this.SelectGender(gender);
						this.EnterEmail(email);;
						
					}
			
			
			
				public void InviteUser(
												String username,
												String password,
												String confirmpassword,
												String passwordhint,
												String secretque,	
												String secretans,
												String firstname,
												String lastname,
												String gender,
												String email) throws InterruptedException
			        {
					FillInviteUserForm(username, password, confirmpassword, passwordhint, secretque, secretans, firstname, lastname, gender, email);
					}	
				
				public void FillInviteUserForm2(
						String username,
						String password,
						String confirmpassword,
						String firstname,
						String lastname,
						String gender,
						String email
						)
					{
						this.EnterUserName(username);
						this.EnterPassword(password);
						this.EnterConfirmPassword(confirmpassword);
						this.EnterFirstName(firstname);
						this.EnterLastName(lastname);
						this.SelectGender(gender);
						this.EnterEmail(email);;

}
				
				
				
}
