package guru.qa.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresInTests {

    /*
        1. Make POST request to https://reqres.in/api/login
        with body {     "email": "eve.holt@reqres.in",     "password": "cityslicka" }
        2. Get response {"token": "QpwL5tke4Pnpja7X4"}
        3. Check token is QpwL5tke4Pnpja7X4
     */

    @Test
    void loginTest(){
        String data = "{     \"email\": \"eve.holt@reqres.in\",     \"password\": \"cityslicka\" }";
        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is ("QpwL5tke4Pnpja7X4"));
    }
    @Test
    void negativeLogin415Test(){
        given()
                .log().uri()
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }

    @Test
    void missingEmailOrUsernameTest(){
        given()
                .log().uri()
                .body("123")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is ("Missing email or username"));
    }
    @Test
    void missingPasswordTest(){
        String data = "{     \"email\": \"eve.holt@reqres.in\" }";
        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is ("Missing password"));
    }
}
