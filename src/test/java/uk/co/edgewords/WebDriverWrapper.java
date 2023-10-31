package uk.co.edgewords;

import org.openqa.selenium.WebDriver;

public class WebDriverWrapper {

    private WebDriver driver;

    //No constructor - we'll be passing this class around and initialising it with picocontainer
    //If the constructor accepted a WebDriver, picocontainer would have no idea where to get it from.
    //Instead once it has been passed around driver will be set by calling setDriver() manually.

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver){ //This method can only be called by passing it a WeBdriver - it wont accept a string or anything else
        this.driver = driver;
    }
}
