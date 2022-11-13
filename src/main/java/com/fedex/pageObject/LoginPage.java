package com.fedex.pageObject;

import com.fedex.BrowserUtils;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class LoginPage extends PageObject {

    private final BrowserUtils browser = new BrowserUtils(getDriver());

    public boolean isLoginPageDisplayed() {
        browser.waitForElement(By.id(LoginPageLocators.LOGIN_BUTTON));
        return browser.elementVisible(By.id(LoginPageLocators.LOGIN_BUTTON));
    }
}
