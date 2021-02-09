package com.salesforce.qa.pages;

import com.salesforce.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class WelcomePage extends TestBase {
    @FindBy(id="phHeaderLogoImage")
    WebElement WelcomePageLogo;

    @FindBy(xpath = "//span[text()[contains(.,'Shruti joshi')]]")
    WebElement loggedInUser;

    @FindBy(id="getting-started-block")
    WebElement gettingStartedBlock;

    @FindBy(id="userNavLabel")
    WebElement userDrpDwn;

    public WelcomePage(){
        PageFactory.initElements(driver, this);
    }

    public boolean verifyWelcomePageBlock(){
        return gettingStartedBlock.isDisplayed();
    }

    public boolean verifyLoggedInUser(){
        return loggedInUser.isDisplayed();
    }

    public boolean verifyUserDropDown(){
        Select userDropdown = new Select();

    }
}
