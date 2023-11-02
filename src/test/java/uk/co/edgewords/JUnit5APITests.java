package uk.co.edgewords;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class JUnit5APITests {

//    @Test
//    void firstAPItest(){
//        RequestSpecification req = given().baseUri("http://localhost:2002");
//        Response res = req.when().get("/api/products");
//        ValidatableResponse valRes = res.then();
//        valRes.statusCode(201).log().headers();
//    }
//
//    @Test
//    void secondAPItest(){
//        RestAssured.given().baseUri("http://localhost:2002")
//                .when().get("/api/products")
//                .then().log().headers().statusCode(201);
//    }
//
//    @Test
//    void thirdAPItest(){
//        given().baseUri("http://localhost:2002")
//                .when().get("/api/products")
//                .then().log().headers().statusCode(201);
//    }


    @BeforeAll
    static void setUpDefaultRequestSpec(){
        RequestSpecification spec = given();
        spec.baseUri("http://localhost");
        spec.port(2002);
        spec.contentType(ContentType.JSON);

        requestSpecification = spec;
    }

    @Test
    void testUsingDefaultSpec(){
        when().get("/api/products/1")
                .then().log().body()
                .assertThat().body("name",equalTo("ipad")).statusCode(200);
    }
}
