package starter.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseStep {

    private static final Logger logger = LoggerFactory.getLogger(BaseStep.class);

    protected WebDriver driver;

    public WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--hide-scrollbars");
        driver = new ChromeDriver(options);
        logger.info("Chromedriver created");
        return driver;
    }


}
