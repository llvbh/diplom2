package api;

import pojo.*;

import static io.restassured.RestAssured.given;

public class UserClient {
    protected final String ROOT = "https://stellarburgers.nomoreparties.site/api/";
    protected final String REGISTER = ROOT + "auth/register";
    protected final String LOGIN = ROOT + "auth/login";

     public ResponseUser createUser(User user, int statusCode, String responseMessage){
        return given()
                .header("Content-type", "application/json")
                .baseUri("https://stellarburgers.nomoreparties.site/api")
                .body(user)
                .post("https://stellarburgers.nomoreparties.site/api/auth/register")
                .then()
                .extract()
                .body()
                .as(ResponseUser.class);
     }

    public ResponseUserWithError doNotCreateUser(User user, int statusCode, String responseMessage){
        return given()
                .header("Content-type", "application/json")
                .baseUri("https://stellarburgers.nomoreparties.site/api")
                .body(user)
                .post("https://stellarburgers.nomoreparties.site/api/auth/register")
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
                .baseUri("https://stellarburgers.nomoreparties.site/api")
                .body(creds)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/login")
                .then()
                .extract()
                .body()
                .as(ResponseUserLoginWithError.class);
    }

    public ResponseEditUser editUser(ResponseUserInfo userInfo, String authorization, int statusCode){
        return given()
                .header("Content-type", "application/json")
                .baseUri("https://stellarburgers.nomoreparties.site/api")
                .auth()
                .oauth2(authorization)
                .body(userInfo)
                .when()
                .patch("https://stellarburgers.nomoreparties.site/api/auth/user")
                .then()
                .extract()
                .body()
                .as(ResponseEditUser.class);
    }
}
