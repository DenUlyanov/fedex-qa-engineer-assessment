package com.fedex;

import net.serenitybdd.core.Serenity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BrowserUtils {

    private static final Logger logger = LoggerFactory.getLogger(BrowserUtils.class);

    Duration webDriverTimeout = Duration.ofSeconds(Long.parseLong(Serenity
            .environmentVariables().getProperty("web.driver.wait")));

    private final WebDriver driver;

    public BrowserUtils(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentURL() {
        String localeUrl = driver.getCurrentUrl();
        logger.info(String.format("Verifying current URL %s", localeUrl));
        return localeUrl;
    }

    public void navigateTo(String url) {
        driver.manage().timeouts().pageLoadTimeout(webDriverTimeout);
        logger.info(String.format("Navigating %s URL", url));
        driver.get(url);
    }

    public void waitForElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, webDriverTimeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void clickElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, webDriverTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }


}
