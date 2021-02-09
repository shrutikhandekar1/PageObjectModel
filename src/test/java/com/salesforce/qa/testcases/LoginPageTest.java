package com.salesforce.qa.testcases;

import com.salesforce.qa.pages.WelcomePage;
import com.salesforce.qa.base.TestBase;
import com.salesforce.qa.pages.LoginPage;
import com.salesforce.qa.util.ExcelUtil;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	WelcomePage welcomePage;

	public LoginPageTest() {
		super();
	}

	@BeforeTest
	public void setupTestData(){
		//set testdata excel and sheet
		ExcelUtil.setExcelFileSheet("LoginData");
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
	public void loginTest_Invalid(String username, String password, String errMessage) throws InterruptedException {
		welcomePage = loginPage.login(username, password);
		System.out.println(username + " " + password + " " + errMessage + "**");
		if(!errMessage.equals(null)){
			Thread.sleep(3000);
			Assert.assertTrue(driver.getPageSource().contains(errMessage));
		}
		Assert.assertEquals(loginPage.validateLoginPageTitle(), "Login | Salesforce");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "LoginData")
	public Object[][] getDataFromDataProvider(Method m) throws Exception {
		switch (m.getName()) {
			case "loginTest_Valid":
				return new Object[][]{{"monak@gmail.com", "Admin1234"}};

			case "loginTest_Invalid":
				Object[][] testObjArray = ExcelUtil.getTableArray(testDataExcelFileName,"InvalidLoginData");

				return (testObjArray);

		}
		return null;
	}

}
