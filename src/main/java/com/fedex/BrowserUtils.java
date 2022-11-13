package com.fedex;

import net.serenitybdd.core.Serenity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class BrowserUtils {

    private static final Logger logger = LoggerFactory.getLogger(BrowserUtils.class);

    Duration webDriverTimeout = Duration.ofSeconds(Long.parseLong(Serenity
            .environmentVariables().getProperty("web.driver.wait")));

    private final WebDriver driver;

    public BrowserUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo(String url) {
        driver.manage().timeouts().pageLoadTimeout(webDriverTimeout);
        driver.manage().window().maximize();
        logger.info(String.format("Navigating %s URL", url));
        driver.get(url);
    }

    public String getCurrentURL() {
        String localeUrl = driver.getCurrentUrl();
        logger.info(String.format("Verifying current URL %s", localeUrl));
        return localeUrl;
    }

    public void waitForElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, webDriverTimeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementToBePresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, webDriverTimeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, webDriverTimeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, webDriverTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void selectFromDropDown(WebElement element, String visibleText) {
        waitForElement(element);
        logger.info(String.format("Selecting dropdown option %s", visibleText));
        Select dropDown = new Select(element);
        dropDown.selectByVisibleText(visibleText);
    }

    public void fillInText(WebElement element, String text) {
        waitForElement(element);
        logger.info(String.format("Entering text %s", text));
        element.sendKeys(text);
    }

    public String getText(By by) {
        waitForElement(by);
        WebElement element = driver.findElement(by);
        String text = element.getText();
        logger.info(String.format("Got text %s", text));
        return text;
    }


    public boolean elementVisible(By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements.size() > 0;
    }


}
