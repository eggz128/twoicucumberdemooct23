package uk.co.edgewords.pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

// page_url = about:blank
public class AddRecordPage {

    private WebDriver driver;
    public AddRecordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //Locators
    @FindBy(css="body")
    WebElement bodyContent;

    //Helpers
    public String getBodyText(){
        return bodyContent.getText();
    }



}