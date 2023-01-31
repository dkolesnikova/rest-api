package guru.qa.tests;

import guru.qa.lombok.LombokUserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static guru.qa.specs.Specs.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestApi {
    @Test
    @DisplayName("Проверка создания пользователя")
    public void createUser() {

        String user ="{     \"name\": \"morpheus\",     \"job\": \"leader\" }";

        LombokUserData data = given()
                .spec(request)
                .when()
                .body(user)
                .post("./users")
                .then()
                .spec(response201)
                .log().all()
                .extract().as(LombokUserData.class);
        assertEquals("morpheus", data.getName());
        assertEquals("leader", data.getJob());

    }

    @Test
    @DisplayName("Неуспешная регистрация пользователя")
    public void unSuccessfulRegisterTest() {
        given()
                .spec(request)
                .when()
                .post("/register")
                .then()
                .log().status()
                .spec(response400);
    }
    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    public void successfulRegisterTest () {
        String user = "{     \"email\": \"eve.holt@reqres.in\",     \"password\": \"pistol\" }";
        given()
                .spec(request)
                .when()
                .body(user)
                .post("/register")
                .then()
                .spec(response200)
                .log().all()
                .body("id", is(4));
    }
    @Test
    @DisplayName("Проверка наличия данных по id")
    public void checkUserId() {
        LombokUserData data = given()
                .spec(request)
                .when()
                .get("/unknown/2")
                .then()
                .spec(response200)
                .log().all()
                .extract().as(LombokUserData.class);
        assertEquals(2,data.getUser().getId());
        assertEquals("fuchsia rose",data.getUser().getName());
    }
    @Test
    public void checkEmailWithGroovy () {
        given()
                .spec(request)
                .when()
                .get("/users?page=2")
                .then()
                .spec(response200)
                .body("data.findAll{it.id == 7}.email", hasItem("michael.lawson@reqres.in"));
    }
    @Test
    @DisplayName("Проверка удаления пользователя")
    public void checkDeleteUser () {
        given()
                .spec(request)
                .when()
                .delete("/users/2")
                .then()
                .spec(response204)
                .log().all();
    }
}
