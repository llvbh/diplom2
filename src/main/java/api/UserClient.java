package api;

import io.restassured.response.Response;
import pojo.*;

public class UserClient extends RestAssuredClient {

     public ResponseUser createUser(User user) {
        return reqSpec
            .body(user)
            .post(REGISTER)
            .then()
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
                .extract().response();
    }

    public ResponseUserLogin loginUser(UserCredentials creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN)
                .then()
                .extract()
                .body()
                .as(ResponseUserLogin.class);
    }

    public ResponseUserLoginWithError loginUserWithError(UserCredentials creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN)
                .then()
                .extract()
                .body()
                .as(ResponseUserLoginWithError.class);
    }

    public ResponseEditUserWithAuth editUser(ResponseUserInfo userInfo, String authorization) {
        return reqSpec
                .auth()
                .oauth2(authorization)
                .body(userInfo)
                .when()
                .patch(USER)
                .then()
                .extract()
                .body()
                .as(ResponseEditUserWithAuth.class);
    }

    public ResponseEditUserWithError doNotEditUser(ResponseUserInfo userInfo) {
        return reqSpec
                .body(userInfo)
                .when()
                .patch(USER)
                .then()
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

    public ResponseUserWithError getOrdersNotAuthUser(String authorization, int statusCode) {
        return reqSpec
                .auth()
                .oauth2(authorization)
                .get(ORDERS)
                .then()
                .statusCode(statusCode)
                .extract()
                .body()
                .as(ResponseUserWithError.class);
    }
}
