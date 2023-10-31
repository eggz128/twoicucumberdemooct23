package uk.co.edgewords.pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePOM {

    private WebDriver driver; //Field to hold the driver for service methods to use

    public HomePOM(WebDriver driver) { //Constructor gets the driver from the calling test
        this.driver = driver; //Put the driver in to the field for use by service methods
        PageFactory.initElements(driver, this);
    }

    //Locators
    @FindBy(partialLinkText = "Login")
    private WebElement loginLink;

    //Service Methods
    public void goLogin(){
        loginLink.click();
    }
}
