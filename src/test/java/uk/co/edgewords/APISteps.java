package uk.co.edgewords;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class APISteps {

    Response res;
    @When("I get product {int}")
    public void i_get_product(Integer prodID) {
        res = when().get("/api/products/" + prodID);
    }
    @Then("it is an {string}")
    public void it_is_an(String expectedProd) {
        res.then().log().body()
                .assertThat().body("name", equalTo(expectedProd));
    }
}
