package api;

import io.restassured.response.Response;
import pojo.*;
import java.util.List;

public class OrderClient extends RestAssuredClient  {

    public List<String> createOrder(String authorization, Ingredients ingredients, int statusCode){
        return reqSpec
            .auth()
            .oauth2(authorization)
            .body(ingredients)
            .post(ORDERS)
            .then()
            .assertThat()
            .statusCode(statusCode)
            .extract()
            .path("order.ingredients._id");
    }

    public Response createOrderIngredients (String authorization, Ingredients ingredients, int statusCode){
        return reqSpec
            .auth()
            .oauth2(authorization)
            .body(ingredients)
            .post(ORDERS)
            .then()
            .assertThat()
            .statusCode(statusCode)
            .extract()
            .response();
    }

    public Response createOrderWithoutIngredients (String authorization, Ingredients ingredients, int statusCode){
        return reqSpec
            .auth()
            .oauth2(authorization)
            .body(ingredients)
            .post(ORDERS)
            .then()
            .assertThat()
            .statusCode(statusCode)
            .extract().response();
    }
}
