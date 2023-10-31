package uk.co.edgewords;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class GoogleSteps { //Note the class/file name DOES NOT need to match the feature file
    //Cucumber will look at ALL classes for potential step definitions.
    private WebDriver driver; //Field to share driver instance between methods in this class
    private String bodyText; //Field to share captured body text between methods in *this* class. Need to use sharedDictionary to hand it to methods outside of this class.
    private final SharedDictionary sharedDict; //Field to use in this class pointing to the shared dictionary injected by picocontainer
    private final WebDriverWrapper wdWrapper;
    public GoogleSteps(SharedDictionary sharedDict, WebDriverWrapper wdWrapper) { //When cucumber inits this class to get the scenario step it will be handed (by picocontainer) the already existing shared dictionary created when hooks ran.
        this.sharedDict = sharedDict; //Put passed sharedDict into field in this class to work with if needed
        //this.driver = (WebDriver) sharedDict.readDict("mydriver"); //Get the driver back from the shared dict and cast it from plain Object to WebDriver. This cast isn't checked at compile time, only run time, so if the "mydriver" was *actually* a String  (not WebDriver) *bang* at runtime.    }
        this.driver = wdWrapper.getDriver();
        this.wdWrapper = wdWrapper;
    }


    @Given("I am on the Google Home page")
    //You can use multiple annotations to handle different phrasings for the same actions
    //@Given("^(?:I|i) am on (?i)GOoG(?-i)le$") //You can use regular expressions to match small variations in a step phrasing
    public void i_am_on_the_google_home_page() { //Method name is not important for step matching, but should be something sensible

        driver.get("https://www.google.com");
        driver.findElement(By.cssSelector("#L2AGLb > div")).click();
        bodyText = driver.findElement(By.tagName("body")).getText();
        sharedDict.addDict("passedBody", bodyText); //Put the captured text into the dictionary for use in other classes.
    }

//    @Given("I/i am on the Google Home page") //This would cause an ambiguous step error if uncommented as one step in the feature file could match more than one automation method here
//    public void alt_gogole(){
//        driver = new ChromeDriver();
//        driver.get("https://www.google.com");
//        driver.findElement(By.cssSelector("#L2AGLb > div")).click();
//    }


    @Then("{string} is the top result") //But Cucumber Expressions are easier to read and use - and are the default now.
    public void is_the_top_result(String result) {
        String topResult = driver.findElement(By.cssSelector("#rso > div.hlcw0c")).getText();
        //Maybe better css
        //
        assertThat("Result not found", topResult, containsString(result)); //Then steps are where we check if things worked - so have assertions
    }

    @Then("I see in the results")
    public void i_see_in_the_results(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        String results = driver.findElement(By.id("rso")).getText();
        for (var row : data) {
            assertThat(results, containsString(row.get("Title")));
            assertThat(results, containsString(row.get("url")));
        }
    }

}
