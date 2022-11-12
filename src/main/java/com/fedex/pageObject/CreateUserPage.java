package com.fedex.pageObject;

import com.fedex.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateUserPage {
    private final BrowserUtils browser;

    public CreateUserPage(WebDriver driver) {
        browser = new BrowserUtils(driver);
    }

    public boolean isCreateUserPageDisplayed() {
        browser.waitForElement(By.id(CreateUserPageLocators.ACCEPT_BUTTON));
        return browser.isElementVisible(By.id(CreateUserPageLocators.ACCEPT_BUTTON));
    }
}
