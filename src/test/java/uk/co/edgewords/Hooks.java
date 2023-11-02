package uk.co.edgewords;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class Hooks {
    //This class will be instantiated first as the @Before is needed first to run a scenario
    private WebDriver driver;
    private final SharedDictionary sharedDict; //Field to work with the dictionary *in this class*

    private final WebDriverWrapper wdWrapper;
    public Hooks(SharedDictionary sharedDict, WebDriverWrapper wdwrapper){ //When Cucumber instantiates this class the constructor will run and picocontainer will create and inject an instance of the dictionary class
        this.sharedDict = sharedDict; //Put the dictionary in this class's field to work with in class methods.
        this.wdWrapper = wdwrapper;
    }


    @Before("@GUI")
    public void setUp(){
        String browser = System.getProperty("BROWSER");
        browser = "firefox";
        if(browser==null){browser="";}
        switch (browser){
            case"chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.out.printf("No or unrecognised driver specified on command line. Using Edge");
                driver = new EdgeDriver();
                break;
        }

        //driver = new ChromeDriver();
        //sharedDict.addDict("mydriver", driver); //We can add any object type to the dictionary. Here the webdriver instance is added with the name "mydriver"
        //sharedDict.addDict("mydriver", "hahanothisisastring"); //This will compile but would throw a runtime class cast exception when another class attempts to retrieve a WebDriver instance, but gets a string instead.

        wdWrapper.setDriver(driver);
        //wdWrapper.setDriver("NotAWebDriver"); //Won't even compile - cant pass a string to .setDriver() - more type safe
    }

//    @Before("@Firefox") //@Before can be specific to specified tags
//    public void setUpFirefox(){
//        driver = new FirefoxDriver();
//    }

    @After("@GUI")
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Before("@API")
    public void setUpAPI(){
        RequestSpecification spec = given();
        spec.baseUri("http://localhost:2002");
        spec.contentType(ContentType.JSON);

        requestSpecification = spec;

    }
}
