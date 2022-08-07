package api;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import pojo.*;
import java.util.List;

public class OrderClient extends RestAssuredClient  {

    private ExtractableResponse<Response> createOrderResponse(String authorization, Ingredients ingredients) {
        return reqSpec
                .auth()
                .oauth2(authorization)
                .body(ingredients)
                .post(ORDERS)
                .then()
                .assertThat()
                .extract();
    }

    public List<String> createOrder(String authorization, Ingredients ingredients) {
        return createOrderResponse(authorization, ingredients)
            .path("order.ingredients._id");
    }

    public Response createOrderIngredients(String authorization, Ingredients ingredients) {
        return createOrderResponse(authorization, ingredients)
            .response();
    }

    public Response createOrderWithoutIngredients(String authorization) {
        return reqSpec
            .auth()
            .oauth2(authorization)
            .post(ORDERS)
            .then()
            .assertThat()
            .extract()
            .response();
    }
}
