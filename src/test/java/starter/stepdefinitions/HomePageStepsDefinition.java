package starter.stepdefinitions;

import com.fedex.pageObject.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePageStepsDefinition extends BaseStep {

    private static final Logger logger = LoggerFactory.getLogger(HomePageStepsDefinition.class);
    private final HomePage homePage = new HomePage(getChromeDriver());

    @Given("customer is on default home page")
    public void customerIsOnDefaultHomePage() {
        homePage.openHomePage();
        homePage.selectLocale("Netherlands", "English");
        homePage.acceptAllCookies();
    }

    @When("customer navigates to home page")
    public void customerNavigateToHomePage() {
        homePage.openHomePage();
    }

    @When("customer calculates shipping rate from {string} and {string} for {string}")
    public void customerCalculatesShippingRateFromAndFor(String from, String to, String packaging) {
        homePage.requestShippingCost(from, to, packaging);
    }

    @Then("verify navigating to {string} and {string} results in correct {string}")
    public void verifyNavigatingToAndResultsInCorrect(String country, String language, String locale) {
        homePage.selectLocale(country, language);
        homePage.verifyLocaleUrl(locale);
    }

    @Then("customer can see shipment price")
    public void customerCanSeeShipmentPrice() {
        homePage.verifyDeliveryRate();

    }

    @After
    public void afterScenario() {
        logger.info("Closing browser window ... ");
        driver.close();
    }
}
