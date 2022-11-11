package com.fedex.pageObject;

import com.fedex.BrowserUtils;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public void verifyLocaleUrl(String locale) {
        String localeUrl = browser.getCurrentURL();
        Assert.assertTrue(String.format("Locale url does not contains expected locale %s", locale),
                localeUrl.contains(locale));
        logger.info(String.format("%1s URL contains expected locale %2s", localeUrl, locale));
    }


}
