package com.fedex.pageObject;

import com.fedex.BrowserUtils;
import com.fedex.location.LocationProvider;
import net.serenitybdd.core.Serenity;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class HomePage {

    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    private final WebDriver driver;
    private final BrowserUtils browser;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        browser = new BrowserUtils(driver);
    }

    public void openHomePage() {
        String baseUrl = Serenity.environmentVariables().getProperty("base.url");
        browser.navigateTo(baseUrl);
    }

    public void selectLocale(String country, String language) {

        browser.waitForElement(By.className(HomePageLocators.LOCALE_COUNTRY));
        browser.waitForElement(By.className(HomePageLocators.LOCALE_COUNTRY_TITLE));
        browser.waitForElement(By.className(HomePageLocators.LOCALE_COUNTRY_URL));

        // Find country
        Optional<WebElement> countryElement = driver.findElements(By.className(HomePageLocators.LOCALE_COUNTRY))
                .stream().filter(x -> x.findElement(By.className(HomePageLocators.LOCALE_COUNTRY_TITLE))
                        .getText().toLowerCase().contains(country.toLowerCase())).findFirst();

        // Find language
        Optional<WebElement> languageElement = countryElement.get().findElements(By.className(HomePageLocators.LOCALE_COUNTRY_URL))
                .stream().filter(x -> x.getText().toLowerCase().contains(language.toLowerCase())).findFirst();

        browser.clickElement(languageElement.get());
    }

    public void acceptAllCookies() {
        browser.clickElement(driver.findElement(By.className(HomePageLocators.COOKIES_WRAPPER)));
        browser.clickElement(driver.findElement(By.className(HomePageLocators.COOKIES_ACCEPT)));
        logger.info("Cookies accepted");
    }

    public void verifyLocaleUrl(String locale) {
        String localeUrl = browser.getCurrentURL();
        Assert.assertTrue(String.format("Locale url does not contains expected locale %s", locale),
                localeUrl.contains(locale));
        logger.info(String.format("%1s URL contains expected locale %2s", localeUrl, locale));
    }


    public void requestShippingCost(String from, String to, String packaging) {

        LocationProvider locationProvider = new LocationProvider();

        browser.clickElement(driver.findElement(By.id(HomePageLocators.RATES_AND_TIMES)));
        browser.waitForElement(By.id(HomePageLocators.RATES_INPUT));
        fillInAddress(driver.findElement(By.id(HomePageLocators.FROM)),
                locationProvider.getLocationDetails(from));
        fillInAddress(driver.findElement(By.id(HomePageLocators.TO)),
                locationProvider.getLocationDetails(to));
        browser.waitForElementToBePresent(By.id(HomePageLocators.PACKAGE_DROPDOWN));
        browser.selectFromDropDown(driver.findElement(By.id(HomePageLocators.PACKAGE_DROPDOWN)), packaging);
        browser.fillInText(driver.findElement((By.id(HomePageLocators.PACKAGE_WEIGHT))), "0.5");
        browser.clickElement(driver.findElement(By.id(HomePageLocators.SHOW_RATES)));
        browser.waitForElementToBePresent(By.cssSelector(HomePageLocators.SHIP_NOW));
    }

    public void verifyDeliveryRate() {

        SoftAssertions softAssertion = new SoftAssertions();

        softAssertion.assertThat(browser.isElementVisible(By.id(HomePageLocators.RATE_SUMMARY)))
                .as("Rate summary is not visible")
                .isEqualTo(true);

        softAssertion.assertThat(browser.isElementVisible(By.id(HomePageLocators.SERVICE_OPTIONS)))
                .as("Service options are not visible")
                .isEqualTo(true);

        softAssertion.assertThat(browser.getText(By.cssSelector(HomePageLocators.RATE_DETAILS)))
                .as("Rate details missing ARRIVES ON info")
                .contains("ARRIVES ON");

        softAssertion.assertThat(browser.getText(By.cssSelector(HomePageLocators.RATE_DETAILS)))
                .as("Rate details missing DELIVERED BY info")
                .contains("DELIVERED BY");

        softAssertion.assertThat(browser.getText(By.cssSelector(HomePageLocators.RATE_DETAILS)))
                .as("Rate details missing DELIVERED BY info")
                .contains("€");

        Assert.assertTrue("SHIP NOW button is missing",
                browser.isElementVisible(By.cssSelector(HomePageLocators.SHIP_NOW)));
    }

    private void fillInAddress(WebElement element, String text) {
        logger.info(String.format("Filling in address %s", text));
        browser.waitForElement(element);
        try {
            element.sendKeys(text);
            Thread.sleep(2000);
            List<WebElement> addresses = driver.findElements(By.id(HomePageLocators.AUTOCOMPLETE));
            addresses.get(0).click();
        } catch (InterruptedException e) {
            logger.error("Failed to enter address");
            throw new RuntimeException(e);
        }
        logger.info("Address filled successfully");
    }

}
