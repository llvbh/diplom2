package api;

import pojo.Order;
import pojo.ResponseUserLoginWithError;
import pojo.ResponseUserWithAuthOrders;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class OrderClient {

//    public String getIngredientId(String ingredientName){
//        return given()
//                .header("Content-type", "application/json")
//                .baseUri("https://stellarburgers.nomoreparties.site/api")
//                //.auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYyZGFlZjNlOTNlYmMyMDAxYjRhNGQ1NCIsImlhdCI6MTY1ODgzNzM0MiwiZXhwIjoxNjU4ODM4NTQyfQ.fs1vAqvLBUj6ImMfggbHPvEYsA7xZ5_S467J7-g25aU")
//                .get("https://stellarburgers.nomoreparties.site/api/orders")
//                .then().assertThat().body("data.name", equalTo(ingredientName))
//               .extract()
//                .path("success");
//     }


    public Order createOrder(String ingredientId){
        return given()
                .header("Content-type", "application/json")
                .baseUri("https://stellarburgers.nomoreparties.site/api")
                .body(ingredientId)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/orders")
                .then()
                .extract()
                .body()
                .as(Order.class);
    }
}
