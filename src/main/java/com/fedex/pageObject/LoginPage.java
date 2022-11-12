package com.fedex.pageObject;

import com.fedex.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final BrowserUtils browser;

    public LoginPage(WebDriver driver) {
        browser = new BrowserUtils(driver);
    }

    public boolean isLoginPageDisplayed() {
        browser.waitForElement(By.id(LoginPageLocators.LOGIN_BUTTON));
        return browser.isElementVisible(By.id(LoginPageLocators.LOGIN_BUTTON));
    }
}
