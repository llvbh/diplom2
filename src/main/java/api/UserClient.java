package api;

import pojo.*;

import static io.restassured.RestAssured.given;

public class UserClient {

    protected final static String ROOT = "https://stellarburgers.nomoreparties.site/api";
    protected final static String REGISTER = ROOT + "/auth/register";
    protected final static String LOGIN = ROOT + "/auth/login";
    protected final static String USER = ROOT + "/auth/user";

     public ResponseUser createUser(User user, int statusCode, String responseMessage){
        return given()
                .header("Content-type", "application/json")
                .baseUri(ROOT)
                .body(user)
                .post(REGISTER)
                .then()
                .extract()
                .body()
                .as(ResponseUser.class);
     }

    public static void deleteUser(String authorization) {
         given()
                .header("Content-type", "application/json")
                .baseUri(ROOT)
                .auth()
                .oauth2(authorization)
                .delete(USER)
                .then()
                .statusCode(202);
    }

    public ResponseUserWithError doNotCreateUser(User user, int statusCode, String responseMessage){
        return given()
                .header("Content-type", "application/json")
                .baseUri(ROOT)
                .body(user)
                .post(REGISTER)
                .then()
                .extract()
                .body()
                .as(ResponseUserWithError.class);
    }

    public ResponseUserLogin loginUser(UserCredentials creds, int statusCode, String responseMessage){
        return given()
                .header("Content-type", "application/json")
                .baseUri("https://stellarburgers.nomoreparties.site/api")
                .body(creds)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/login")
                .then()
                .extract()
                .body()
                .as(ResponseUserLogin.class);
    }

    public ResponseUserLoginWithError loginUserWithError(UserCredentials creds, int statusCode, String responseMessage){
        return given()
                .header("Content-type", "application/json")
                .baseUri(ROOT)
                .body(creds)
                .when()
                .post(LOGIN)
                .then()
                .extract()
                .body()
                .as(ResponseUserLoginWithError.class);
    }

    public ResponseEditUserWithAuth editUser(ResponseUserInfo userInfo, String authorization){
        return given()
                .header("Content-type", "application/json")
                .baseUri(ROOT)
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

    public ResponseEditUserWithError doNotEditUser(ResponseUserInfo userInfo){
        return given()
                .header("Content-type", "application/json")
                .baseUri(ROOT)
                .body(userInfo)
                .when()
                .patch(USER)
                .then()
                .extract()
                .body()
                .as(ResponseEditUserWithError.class);
    }

    public ResponseUserWithAuthOrders getOrdersAuthUser(String authorization){
        return given()
                .header("Content-type", "application/json")
                .baseUri(ROOT)
                .auth()
                .oauth2(authorization)
                .get("https://stellarburgers.nomoreparties.site/api/orders")
                .then()
                .extract()
                .body()
                .as(ResponseUserWithAuthOrders.class);
    }

    public ResponseUserWithError getOrdersNotAuthUser(String authorization){
        return given()
                .header("Content-type", "application/json")
                .baseUri(ROOT)
                .auth()
                .oauth2(authorization)
                .get("https://stellarburgers.nomoreparties.site/api/orders")
                .then()
                .extract()
                .body()
                .as(ResponseUserWithError.class);
    }
}
