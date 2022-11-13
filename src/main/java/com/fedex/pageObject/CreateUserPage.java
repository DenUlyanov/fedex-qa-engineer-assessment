package com.fedex.pageObject;

import com.fedex.BrowserUtils;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class CreateUserPage extends PageObject {
    private final BrowserUtils browser = new BrowserUtils(getDriver());

    public boolean isCreateUserPageDisplayed() {
        browser.waitForElement(By.id(CreateUserPageLocators.ACCEPT_BUTTON));
        return browser.elementVisible(By.id(CreateUserPageLocators.ACCEPT_BUTTON));
    }
}
