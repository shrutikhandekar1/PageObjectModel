package com.salesforce.qa.testcases;

import com.salesforce.qa.pages.WelcomePage;
import com.salesforce.qa.base.TestBase;
import com.salesforce.qa.pages.LoginPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	WelcomePage welcomePage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	public void loginPageLogoTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertTrue(loginPage.validateSalesforceLogo());
	}

	@Test(priority = 2, dataProvider = "LoginData")
	public void loginTest_Valid(String username, String password) {
		welcomePage = loginPage.login(username, password);
		Assert.assertTrue(welcomePage.verifyWelcomePageBlock());
	}

	@Test(priority = 2, dataProvider = "LoginData")
	public void loginTest_Invalid(String username, String password, String errMessage) {
		welcomePage = loginPage.login(username, password);
		if(!errMessage.equals("")){
			Assert.assertTrue(driver.getPageSource().contains(errMessage));
		}
		Assert.assertEquals(loginPage.validateLoginPageTitle(), "Login | Salesforce");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "LoginData")
	public Object[][] getDataFromDataProvider(Method m) {
		switch (m.getName()) {
			case "loginTest_Valid":
				return new Object[][]{{"monak@gmail.com", "Admin1234"}};

			case "loginTest_Invalid":
				return new Object[][]
						{
								{"", "", ""},
								{"monak@gmail.com", "", "Please enter your password."},
								{"", "Admin1234", ""},
								{"abc", "xyz", "Please check your username and password. If you still can't log in, contact your Salesforce administrator."}
						};
		}
		return null;
	}

}
