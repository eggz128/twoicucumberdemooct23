package uk.co.edgewords.pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPOM {

    private WebDriver driver;

    public LoginPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //Never assert in the POM. Ever.
        //...except maybe here in the constructor...
        //You could check that when the class is instantiated you are on the correct page (sort of a sanity check):
        //Assertions.assertTrue(driver.getTitle()=="Login Page","Should have been on log in page but we are not");
        //(Note that the title of this site is unhelpfully always "Automated Tools Test Site" so this assertion would fail)
    }

    //Locators
    @FindBy(css = "#username")
    //@CacheLookup //Normally page factory will re-search for an element whenever it is used. This mitigates stale element exceptions
                    //However, if you are accessing an element *a lot* and you are sure it is not going to go stale, you could cache the initial lookup.
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(linkText = "Submit")
    public WebElement submitButton;

    //Service Methods
    public LoginPOM setUsername(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
        return this; //By returning "this" instance of the class you can chain method calls in the test.
    }

    public LoginPOM setPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public void submitLogin(){
        submitButton.click();
        //I'd recommend *not* returning either "this" class or a new POM class if a navigation event occurs.
        //i.e. dont allow chaining commands if you're going to a new page.
    }

    //Higher level helpers
    public void login(String username,String password){
        setUsername(username);
        setPassword(password);
        submitLogin();
    }

    public Boolean loginExpectSuccess(String validUsername, String validPassword){
        login(validUsername,validPassword);
        try{
            driver.switchTo().alert();
        }catch (Exception e) {
            return true; //We must have logged in return true
        }
        return false; //Did not login as there was an alert
        //Note : No assertions here. It's not up to the POM Class to decide it what has happened is good or bad
        //Just return values to the test for it to decide if that is what was expected.
    }

    public void LoginExpectFail(String invalidUsername, String invalidPassword){
        //ToDo: different results expected so different method
    }



}
