package com.fedex.pageObject;

import com.fedex.BrowserUtils;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class OpenAccountPage extends PageObject {
    private final BrowserUtils browser = new BrowserUtils(getDriver());

    public boolean isOpenAccountPageDisplayed() {
        browser.waitForElement(By.xpath(OpenAccountPageLocators.OPEN_ACCOUNT_BUTTON));
        return browser.elementVisible(By.xpath(OpenAccountPageLocators.OPEN_ACCOUNT_BUTTON));
    }
}
