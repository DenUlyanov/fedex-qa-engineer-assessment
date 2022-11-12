package starter.stepdefinitions;

import com.fedex.pageObject.CreateUserPage;
import com.fedex.pageObject.HomePage;
import com.fedex.pageObject.LoginPage;
import com.fedex.pageObject.OpenAccountPage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
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

    @Then("verify login page is accessible from home page")
    public void verifyLoginPageIsAccessibleFromHomePage() {
        LoginPage loginPage = homePage.navigateToLoginPage();
        Assert.assertTrue("Login page is not displayed",
                loginPage.isLoginPageDisplayed());
    }

    @Then("verify create user page is accessible from home page")
    public void verifyCreateUserPageIsAccessibleFromHomePage() {
        CreateUserPage createUserPage = homePage.navigateToCreateUserPage();
        Assert.assertTrue("Create user page is not displayed",
                createUserPage.isCreateUserPageDisplayed());
    }

    @Then("verify open account page is accessible from home page")
    public void verifyOpenAccountPageIsAccessibleFromHomePage() {
        OpenAccountPage openAccountPage = homePage.navigateToOpenAccountPage();
        Assert.assertTrue("Open account page is not displayed",
                openAccountPage.isOpenAccountPageDisplayed());
    }
}
