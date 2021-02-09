package com.salesforce.qa.pages;

import com.salesforce.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class WelcomePage extends TestBase {
    @FindBy(id="phHeaderLogoImage")
    WebElement WelcomePageLogo;

    //@FindBy(xpath = "//span[text()[contains(.,'Shruti joshi')]]")
    @FindBy(id="userNavLabel")
    WebElement loggedInUser;

    @FindBy(id="getting-started-block")
    WebElement gettingStartedBlock;

    @FindBy(id="userNav-arrow")
    WebElement userNavBtn;

    @FindBy(id="userNav")
    WebElement userNavDrpDwn;

    public WelcomePage(){
        PageFactory.initElements(driver, this);
    }

    public boolean verifyWelcomePageBlock(){
        return gettingStartedBlock.isDisplayed();
    }

    public String getLoggedInUser(){
        return loggedInUser.getText();
    }

    public List<WebElement> getUserDropDownItems(){
        userNavBtn.click();
        Select userDropdown = new Select(userNavDrpDwn);
        return userDropdown.getOptions();
    }
}
