package com.fedex.pageObject;

import com.fedex.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenAccountPage {
    private final BrowserUtils browser;

    public OpenAccountPage(WebDriver driver) {
        browser = new BrowserUtils(driver);
    }

    public boolean isOpenAccountPageDisplayed() {
        browser.waitForElement(By.xpath(OpenAccountPageLocators.OPEN_ACCOUNT_BUTTON));
        return browser.isElementVisible(By.xpath(OpenAccountPageLocators.OPEN_ACCOUNT_BUTTON));
    }
}
