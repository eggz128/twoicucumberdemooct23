package uk.co.edgewords;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import uk.co.edgewords.pompages.AddRecordPage;
import uk.co.edgewords.pompages.LoginPOM;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class WebDriverSiteSteps {

    private WebDriver driver;
    private final WebDriverWrapper wdwrapper;


    public WebDriverSiteSteps(WebDriverWrapper wdwrapper) {
        this.wdwrapper = wdwrapper;
        this.driver = wdwrapper.getDriver();
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://www.edgewordstraining.co.uk/webdriver2/sdocs/auth.php");

    }
    @When("I use the username {string} and password {string} to login")
    public void i_use_the_username_and_password_to_login(String username, String password) {
        LoginPOM loginPage = new LoginPOM(driver);
        loginPage.login(username, password);
    }
    @Then("I am successfully logged in")
    public void i_am_successfully_logged_in() {
        AddRecordPage addPage = new AddRecordPage(driver);
        String bodyText = addPage.getBodyText();
        assertThat(bodyText, containsString("User is Logged in"));
    }
}
