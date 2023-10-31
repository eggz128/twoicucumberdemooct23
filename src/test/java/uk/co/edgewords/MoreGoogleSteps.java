package uk.co.edgewords;

import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MoreGoogleSteps {

    private WebDriver driver;

    private final SharedDictionary sharedDict;
    private final WebDriverWrapper wdWrapper;

    public MoreGoogleSteps(SharedDictionary sharedDict, WebDriverWrapper wdWrapper){
        this.sharedDict = sharedDict;
        //this.driver = (WebDriver)sharedDict.readDict("mydriver");
        this.wdWrapper = wdWrapper;
        this.driver = wdWrapper.getDriver();
    }



    @When("^I search for \"([^\"]*)\"$") //Reg ex can be used to capture parameters from a step
    public void i_search_for(String searchTerm) { //And pass them through as arguments to a method...
        System.out.println((String)sharedDict.readDict("passedBody")); //Get the text originally captured in GoogleSteps i_am_on_the_google_home_page() - stored safely in the dict.

        driver.findElement(By.name("q")).click(); //Click first or the drop down search suggestions may not appear
        driver.findElement(By.name("q")).sendKeys(searchTerm);
        WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(3)); //Wait for the second Google Search button
        WebElement search = mywait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.lJ9FBc:nth-child(11) > center:nth-child(2) > input[aria-label='Google Search']")));
        search.click();
    }
}
