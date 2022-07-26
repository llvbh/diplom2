package api;

import io.restassured.response.Response;
import pojo.*;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class OrderClient {


    public List<String> createOrder(String authorization, Ingredients ingredients, int statusCode){
        return given()
            .header("Content-type", "application/json")
            .baseUri("https://stellarburgers.nomoreparties.site/api")
            .auth()
            .oauth2(authorization)
            .body(ingredients)
            .post("https://stellarburgers.nomoreparties.site/api/orders")
            .then()
           //.log().all()
            .assertThat()
            .statusCode(statusCode)
            .extract()
            .path("order.ingredients._id");


    }

    public Response createOrder2 (String authorization, Ingredients ingredients, int statusCode){
        return   given()
                .header("Content-type", "application/json")
                .baseUri("https://stellarburgers.nomoreparties.site/api")
                .auth()
                .oauth2(authorization)
                .body(ingredients)
                .post("https://stellarburgers.nomoreparties.site/api/orders")
                .then()
                //.log().all()
                .assertThat()
                .statusCode(statusCode)
                .extract().response();
    }

    public Response  createOrder3 (String authorization, Ingredients ingredients, int statusCode){
        return   given()
                .header("Content-type", "application/json")
                .baseUri("https://stellarburgers.nomoreparties.site/api")
                .auth()
                .oauth2(authorization)
                //.body(ingredients)
                .post("https://stellarburgers.nomoreparties.site/api/orders")
                .then()
                //.log().all()
                .assertThat()
                .statusCode(statusCode)
                .extract().response();


    }

}
