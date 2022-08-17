package api;

import io.restassured.response.Response;
import pojo.*;

public class UserClient extends RestAssuredClient {

     public ResponseUser createUser(User user, int statusCode) {
        return reqSpec
            .body(user)
            .post(REGISTER)
            .then()
             .assertThat()
             .statusCode(statusCode)
             .extract()
            .body()
            .as(ResponseUser.class);
     }

    public static void deleteUser(String authorization) {
         reqSpec
            .auth()
            .oauth2(authorization)
            .delete(USER)
            .then()
            .statusCode(202);
    }

    public Response doNotCreateUser(User user, int statusCode) {
        return reqSpec
                .body(user)
                .post(REGISTER)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    public ResponseUserLogin loginUser(UserCredentials creds, int statusCode) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract()
                .body()
                .as(ResponseUserLogin.class);
    }

    public ResponseEditUserWithError loginUserWithError(UserCredentials creds, int statusCode) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract()
                .body()
                .as(ResponseEditUserWithError.class);
    }

    public ResponseEditUserWithAuth editUser(ResponseUserInfo userInfo, String authorization, int statusCode) {
        return reqSpec
                .auth()
                .oauth2(authorization)
                .body(userInfo)
                .when()
                .patch(USER)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract()
                .body()
                .as(ResponseEditUserWithAuth.class);
    }

    public ResponseEditUserWithError doNotEditUser(ResponseUserInfo userInfo, int statusCode) {
        return reqSpec
                .body(userInfo)
                .when()
                .patch(USER)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract()
                .body()
                .as(ResponseEditUserWithError.class);
    }

    public ResponseUserWithAuthOrders getOrdersAuthUser(String authorization) {
        return reqSpec
                .auth()
                .oauth2(authorization)
                .get(ORDERS)
                .then()
                .extract()
                .body()
                .as(ResponseUserWithAuthOrders.class);
    }

    public ResponseUserWithError getOrdersNotAuthUser(String authorization) {
        return reqSpec
                .auth()
                .oauth2(authorization)
                .get(ORDERS)
                .then()
                .extract()
                .body()
                .as(ResponseUserWithError.class);
    }
}
