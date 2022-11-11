package starter.stepdefinitions;

import com.fedex.pageObject.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePageStepsDefinition extends BaseStep {

    private static final Logger logger = LoggerFactory.getLogger(HomePageStepsDefinition.class);
    private final HomePage homePage = new HomePage(getChromeDriver());

    @When("customer navigates to home page")
    public void customerNavigateToHomePage() {
        homePage.openHomePage();
    }

    @Then("verify navigating to {string} and {string} results in correct {string}")
    public void verifyNavigatingToAndResultsInCorrect(String country, String language, String locale) {
        homePage.selectLocale(country, language);
        homePage.verifyLocaleUrl(locale);
    }

    @After
    public void afterScenario() {
        logger.info("Closing browser window ... ");
        driver.close();
    }
}
