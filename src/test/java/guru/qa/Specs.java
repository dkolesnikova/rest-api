package guru.qa;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;


public class Specs {
    public static RequestSpecification request = with()
            .baseUri("https://reqres.in")
            .basePath("/api")
            .log().uri()
            .contentType(ContentType.JSON);

    public static ResponseSpecification response200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
    public static ResponseSpecification response201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();
    public static ResponseSpecification response204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .build();
    public static ResponseSpecification response400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .build();
}
