package com.salesforce.qa.testcases;

import com.salesforce.qa.base.TestBase;
import com.salesforce.qa.pages.LoginPage;
import com.salesforce.qa.pages.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WelcomePageTest extends TestBase {
    WelcomePage welcomePage;
    LoginPage loginPage;

    public WelcomePageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        welcomePage = new WelcomePage();
    }
    @Test
    public void verifyLoggedInUser(){
        Assert.assertEquals(welcomePage.getLoggedInUser(),prop.getProperty("loggedInUser"));
    }
    @Test
    public void verifyUserDropDownItems(){
        List<WebElement> userDrpDwnItems = welcomePage.getUserDropDownItems();
        List<String> originalList = new ArrayList<String>();
        for (WebElement webElement : userDrpDwnItems) {
            originalList.add(webElement.getText());
        }
    }

}
