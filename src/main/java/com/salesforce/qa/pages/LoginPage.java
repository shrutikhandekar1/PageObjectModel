package com.salesforce.qa.pages;

import com.salesforce.qa.base.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {
	
	//Page Factory - OR:
	@CacheLookup
	@FindBy(id="username")
	WebElement username;

	@CacheLookup
	@FindBy(id="password")
	WebElement password;

	@CacheLookup
	@FindBy(id="Login")
	WebElement loginBtn;

	@FindBy(id="logo")
	WebElement salesforceLogo;

	@FindBy(id="error")
	public WebElement loginErrMessage;

	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	public boolean validateSalesforceLogo(){
		return salesforceLogo.isDisplayed();
	}
	
	public WelcomePage login(String un, String pwd){
		username.clear();
		username.sendKeys(un);
		password.sendKeys(pwd);
		//loginBtn.click();
		    	JavascriptExecutor js = (JavascriptExecutor)driver;
		    	js.executeScript("arguments[0].click();", loginBtn);
		    	
		return new WelcomePage();
	}
	
}
